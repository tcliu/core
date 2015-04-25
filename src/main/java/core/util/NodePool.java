package core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class NodePool {
	
	private Map<String,Node> nodeMap;
	
	private static final SAXParser SAX_PARSER = getSAXParser();
	
	private static final Pattern PROP_REF_PATTERN = Pattern.compile("(.+?)/(.+?)", Pattern.DOTALL);
	
	private static final PropertyResolver DEFAULT_PROP_RESOLVER = (Node node, NodePool nodePool, String expr) -> {
		Matcher matcher = PROP_REF_PATTERN.matcher(expr);
		if (matcher.matches()) {
			node = nodePool.getNode(matcher.group(1));
		}
		String key = matcher.matches() ? matcher.group(2) : expr;
		return node.getProperty(key);
	};
	
	private static SAXParser getSAXParser() {
		try {
			return SAXParserFactory.newInstance().newSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			return null;
		}
	}
	
	public NodePool(File xmlFile) throws IOException, SAXException {
		Collection<Node> nodes = load(xmlFile);
		initNodeMap(nodes);
	}
	
	public NodePool(InputStream is) throws IOException, SAXException {
		Collection<Node> nodes = load(is);
		initNodeMap(nodes);
	}
	
	public NodePool(Collection<Node> nodes) {
		initNodeMap(nodes);
	}
	
	public Map<String,Node> getNodeMap() {
		return nodeMap;
	}
	
	public Collection<Node> getNodes() {
		return nodeMap.values();
	}
	
	public Node getNode(String id) {
		return nodeMap.get(id);
	}
	
	public Collection<Node> load(File xmlFile) throws IOException, SAXException {
		try (InputStream is = new FileInputStream(xmlFile)) {
			return load(is);
		}
	}
	
	public Collection<Node> load(InputStream is) throws IOException, SAXException {
		Collection<Node> nodes = new ArrayList<>();
		load(is, nodes);
		return nodes;
	}
	
	public Node resolveNode(String id) {
		return resolveNode(id, DEFAULT_PROP_RESOLVER);
	}
	
	public Node resolveNode(String id, PropertyResolver propertyResolver) {
		return resolveNode(getNode(id), propertyResolver);
	}
	
	private void load(InputStream is, Collection<Node> nodes) throws IOException, SAXException {
		SAX_PARSER.parse(is, new NodeHandler(nodes));
	}
	
	public Node resolveNode(Node node, PropertyResolver propertyResolver) {
		if (node == null)
			return null;
		Node resolvedNode = new Node(node);
		Map<String,String> cache = new HashMap<>();
		for (Map.Entry<String,String> entry : resolvedNode.getProperties().entrySet()) {
			entry.setValue( resolveProperty(resolvedNode, entry.getValue(), propertyResolver, false, cache, new HashSet<String>()) );
		}
		return resolvedNode;
	}
	
	private String resolveProperty(Node node, String expr, PropertyResolver propertyResolver, boolean isResolveProp, Map<String,String> cache, Collection<String> stack) {
		String resolvedProp = cache.get(expr);
		if (resolvedProp == null) {
			if (expr == null || stack.contains(expr))
				return null;
			stack.add(expr);
			StringBuilder sb = new StringBuilder();
			int s = -1, e = -1, depth = 0;
			for (int i=0; i<expr.length(); i++) {
				if (expr.charAt(i) == '{') {
					if (depth++ == 0) {
						sb.append( expr.substring(e + 1, i) );
						s = i;
					}
				} else if (expr.charAt(i) == '}') {
					if (--depth == 0) {
						sb.append( resolveProperty(node, expr.substring(s + 1, i), propertyResolver, true, cache, stack) );
						e = i;
					}
				}
			}
			sb.append( expr.substring(e + 1) );
			String prop = sb.toString();
			resolvedProp = prop;
			if (isResolveProp)
				resolvedProp = propertyResolver.resolveProperty(node, this, resolvedProp);
			resolvedProp = resolveProperty(node, resolvedProp, propertyResolver, false, cache, stack);
			if (resolvedProp == null)
				resolvedProp = prop;
			cache.put(expr, resolvedProp);
		}
		return resolvedProp;
	}
	
	private void initNodeMap(Collection<Node> nodes) {
		this.nodeMap = new HashMap<>(nodes.size());
		for (Node node : nodes) {
			if (node.getId() != null) {
				if (getNode(node.getId()) != null)
					throw new RuntimeException("Duplicate node id: " + node.getId());
				this.nodeMap.put(node.getId(), node);
			}
		}
		for (Node node : getNodes()) {
			String[] parents = node.getParents();
			if (parents != null) {
				for (String parent : parents) {
					Node parentNode = nodeMap.get(parent);
					if (parentNode == null)
						throw new RuntimeException("Parent node not found: " + parent);
					node.getParentNodes().add(parentNode);
				}
			}
		}
		for (Node node : getNodes()) {
			inheritParentProperties(node);
		}
	}
	
	private void inheritParentProperties(Node node) {
		for (Node parentNode : node.getParentNodes()) {
			inheritParentProperties(parentNode);
			node.addProperties(parentNode.getProperties());
		}
	}
	
	public static class Node {
		
		private final String name;
		
		private final Map<String,String> properties = new LinkedHashMap<>();
		
		private final Collection<Node> parentNodes = new ArrayList<>(); 
		
		public Node(String name) {
			this.name = name;
		}
		
		public Node(Node node) {
			this.name = node.getName();
			this.properties.putAll(node.getProperties());
		}
		
		public String getId() {
			return getProperty("id");
		}
		
		public String getName() {
			return name;
		}
		
		public String[] getParents() {
			String parents = getProperty("parents");
			return parents != null ? parents.split(",") : null;
		}
		
		public String getProperty(String key) {
			return properties.get(key);
		}
		
		public Map<String,String> getProperties() {
			return properties;
		}
		
		public void addProperties(Map<String,String> props) {
			for (Map.Entry<String,String> entry : props.entrySet()) {
				properties.putIfAbsent(entry.getKey(), entry.getValue());
			}
		}
		
		public Collection<Node> getParentNodes() {
			return parentNodes;
		}

		@Override
		public String toString() {
			return name + " " + properties;
		}
		
	}

	private class NodeHandler extends DefaultHandler {

		private final Collection<Node> nodes;
		
		private Node currentNode;

		private String currentValue;
		
		private int currentDepth = 0;
		
		public NodeHandler(Collection<Node> nodes) {
			this.nodes = nodes;
		}
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			if (currentDepth == 1) {
				// System.out.printf("startElement %s\n", qName);
				currentNode = new Node(qName);
				for (int i=0; i<attributes.getLength(); i++) {
					String key = attributes.getQName(i), value = attributes.getValue(i).trim(); 
					currentNode.getProperties().putIfAbsent(key, value);
				}
			}
			currentDepth++;
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			currentDepth--;
			if (currentDepth == 1) {
				// System.out.printf("endElement %s\n", qName);
				nodes.add(currentNode);
				currentNode = null;
			} else if (currentDepth == 2) {
				currentNode.getProperties().putIfAbsent(qName, currentValue);
				currentValue = null;
			}
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			if (currentDepth == 3 && currentValue == null) {
				currentValue = new String(ch, start, length).trim();
			}
		}
	}
	
	interface PropertyResolver {
		
		String resolveProperty(Node node, NodePool nodePool, String expr);
		
	}
	
}
