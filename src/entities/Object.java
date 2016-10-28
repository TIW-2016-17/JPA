package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Objects database table.
 * 
 */
@Entity
@Table(name="Objects")
@NamedQuery(name="Object.findAll", query="SELECT o FROM Object o")
public class Object implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	/*	another way
	 * @TableGenerator(name="EVENT_GEN2",
			table="sequences",
			pkColumnName="seq_name",
			valueColumnName="seq_number",
			pkColumnValue="SEQUENCE",		
			allocationSize=1)
		@GeneratedValue(strategy=GenerationType.TABLE,
						generator="EVENT_GEN2")*/
	@Column(name="id_object")
	private int idObject;

	private String description;

	private String name;

	private String route;

	//bi-directional many-to-many association to Cours
	@ManyToMany
	@JoinTable(
		name="CoursesObjects"
		, joinColumns={
			@JoinColumn(name="id_object")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_course")
			}
		)
	private List<Cours> courses;

	public Object() {
	}

	public int getIdObject() {
		return this.idObject;
	}

	public void setIdObject(int idObject) {
		this.idObject = idObject;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoute() {
		return this.route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public List<Cours> getCourses() {
		return this.courses;
	}

	public void setCourses(List<Cours> courses) {
		this.courses = courses;
	}

}