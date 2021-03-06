package proof.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SequenceGenerator;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "SBDATAAC_PETS")
@NamedEntityGraph(name = "Pet.type",
				  attributeNodes = @NamedAttributeNode("type"))
@NamedStoredProcedureQuery(name = "Pet.plus1", procedureName = "PRC_PLUS1INOUT", parameters = {
		  @StoredProcedureParameter(mode = ParameterMode.IN, name = "arg", type = Integer.class),
		  @StoredProcedureParameter(mode = ParameterMode.OUT, name = "res", type = Integer.class) })
public class Pet {

	@Id
	@Column(name = "PET_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_seq")
	@SequenceGenerator(name = "pet_seq", sequenceName = "SBDATAAC_PETS_PET_ID_SEQ")
	private Long id;
	
	@Version
	@Column(name = "VERSION")
	private long version;
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "BIRTH_DATE")
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name = "PET_TYPE_ID")
    private PetType type;

	public Long getId() {
	    return id;
	}

	public void setId(Long id) {
	    this.id = id;
	}

    public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public PetType getType() {
        return this.type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
