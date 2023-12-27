package ma.projet.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Groupe.
 */
@Entity
@Table(name = "groupe")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Groupe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "annee")
    private String annee;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "user", "groupes" }, allowSetters = true)
    private Professor professor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupe")
    @JsonIgnoreProperties(value = { "user", "groupe" }, allowSetters = true)
    private Set<Student> students = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groupes")
    @JsonIgnoreProperties(value = { "tooth", "groupes" }, allowSetters = true)
    private Set<PW> pws = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Groupe id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Groupe code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAnnee() {
        return this.annee;
    }

    public Groupe annee(String annee) {
        this.setAnnee(annee);
        return this;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public Professor getProfessor() {
        return this.professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Groupe professor(Professor professor) {
        this.setProfessor(professor);
        return this;
    }

    public Set<Student> getStudents() {
        return this.students;
    }

    public void setStudents(Set<Student> students) {
        if (this.students != null) {
            this.students.forEach(i -> i.setGroupe(null));
        }
        if (students != null) {
            students.forEach(i -> i.setGroupe(this));
        }
        this.students = students;
    }

    public Groupe students(Set<Student> students) {
        this.setStudents(students);
        return this;
    }

    public Groupe addStudents(Student student) {
        this.students.add(student);
        student.setGroupe(this);
        return this;
    }

    public Groupe removeStudents(Student student) {
        this.students.remove(student);
        student.setGroupe(null);
        return this;
    }

    public Set<PW> getPws() {
        return this.pws;
    }

    public void setPws(Set<PW> pWS) {
        if (this.pws != null) {
            this.pws.forEach(i -> i.removeGroupe(this));
        }
        if (pWS != null) {
            pWS.forEach(i -> i.addGroupe(this));
        }
        this.pws = pWS;
    }

    public Groupe pws(Set<PW> pWS) {
        this.setPws(pWS);
        return this;
    }

    public Groupe addPw(PW pW) {
        this.pws.add(pW);
        pW.getGroupes().add(this);
        return this;
    }

    public Groupe removePw(PW pW) {
        this.pws.remove(pW);
        pW.getGroupes().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Groupe)) {
            return false;
        }
        return getId() != null && getId().equals(((Groupe) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Groupe{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", annee='" + getAnnee() + "'" +
            "}";
    }
}
