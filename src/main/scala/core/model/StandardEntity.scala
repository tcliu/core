package core.model

import javax.persistence.MappedSuperclass
import javax.persistence.Temporal
import javax.persistence.Column
import javax.persistence.TemporalType
import java.util.Date
import javax.persistence.Version

@MappedSuperclass
class StandardEntity {

	@Column(name = "UPDATED")
	@Temporal(TemporalType.TIMESTAMP)
	@Version
	protected var _dateUpdated : Date = _

	@Column(name = "CREATED")
	@Temporal(TemporalType.TIMESTAMP)
	protected var _dateCreated : Date = _

}