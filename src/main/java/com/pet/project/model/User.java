package com.pet.project.model;

import com.pet.project.model.task.Task;
import com.pet.project.model.day.Day;
import com.pet.project.model.meeting.Meeting;
import com.pet.project.model.office_structure.BranchOffice;
import com.pet.project.model.office_structure.Department;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="APP_USER")
public class User implements Serializable{

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	@Column(name="SSO_ID", unique=true, nullable=false)
	private String ssoId;
	@NotEmpty
	@Column(name="PASSWORD", nullable=false)
	private String password;

	@NotEmpty
	@Column(name="FIRST_NAME", nullable=false)
	private String firstName;

	@NotEmpty
	@Column(name="LAST_NAME", nullable=false)
	private String lastName;

	@NotEmpty
	@Column(name="EMAIL", nullable=false)
	private String email;

	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "APP_USER_USER_PROFILE",
             joinColumns = { @JoinColumn(name = "USER_ID") },
             inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
	private Set<UserProfile> userProfiles = new HashSet<>();

	@Column(name = "ISONLINE")
	private boolean isOnline=false;
	@Column(name = "PATRONYMIC")
	private String patronymic;
	@Column(name = "BIRTHDAY")
	private String birthday;
	@Column(name = "PHONE")
	private String phone;
	@Column(name = "WORK_PHONE")
	private String work_phone;
	@Column(name = "INTEROFFICE_PHONE")
	private String interoffice_phone;
	@Column(name = "POSITION")
	private String position;
	@OneToMany(mappedBy = "subordinator", cascade = CascadeType.ALL)
	private Set<User> subordinate = new HashSet<>();
	@ManyToOne
	@JoinColumn(name = "id_subordinator")
	private User subordinator;
	@OneToMany(mappedBy = "userWhoCreate", cascade = CascadeType.ALL)
	private Set<Task> taskCreatedBy = new HashSet<>();
	@OneToMany(mappedBy = "userWhoDo", cascade = CascadeType.ALL)
	private Set<Task> taskCreatedFor = new HashSet<>();
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Day> days = new HashSet<>();

	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinTable(name = "MEET_USER",
			joinColumns = {@JoinColumn(name = "ID_USER")},
			inverseJoinColumns = {@JoinColumn(name = "ID_MEETING")})
	private Set<Meeting> meetings = new HashSet<>();
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinTable(name = "us_col",
			joinColumns = {@JoinColumn(name = "ID_USER")},
			inverseJoinColumns = {@JoinColumn(name = "id_task")})
	private Set<Task> coltasks = new HashSet<>();
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "manager", cascade = CascadeType.ALL)
	private Department dept_where_head;
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "manager", cascade = CascadeType.ALL)
	private BranchOffice branch_where_head;
	private  String fullname;

	public String getFullname() {
		fullname= firstName+" "+patronymic+" "+lastName;

		return fullname;
	}

	public Set<Task> getColtasks() {
		return coltasks;
	}

	public void setColtasks(Set<Task> coltasks) {
		this.coltasks = coltasks;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Department getDept_where_head() {
		return dept_where_head;
	}

	public void setDept_where_head(Department dept_where_head) {
		this.dept_where_head = dept_where_head;
	}

	public BranchOffice getBranch_where_head() {
		return branch_where_head;
	}

	public void setBranch_where_head(BranchOffice branch_where_head) {
		this.branch_where_head = branch_where_head;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean online) {
		isOnline = online;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWork_phone() {
		return work_phone;
	}

	public void setWork_phone(String work_phone) {
		this.work_phone = work_phone;
	}

	public String getInteroffice_phone() {
		return interoffice_phone;
	}

	public void setInteroffice_phone(String interoffice_phone) {
		this.interoffice_phone = interoffice_phone;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Set<User> getSubordinate() {
		return subordinate;
	}

	public void setSubordinate(Set<User> subordinate) {
		this.subordinate = subordinate;
	}

	public User getSubordinator() {
		return subordinator;
	}

	public void setSubordinator(User subordinator) {
		this.subordinator = subordinator;
	}

	public Set<Task> getTaskCreatedBy() {
		return taskCreatedBy;
	}

	public void setTaskCreatedBy(Set<Task> taskCreatedBy) {
		this.taskCreatedBy = taskCreatedBy;
	}

	public Set<Task> getTaskCreatedFor() {
		return taskCreatedFor;
	}

	public void setTaskCreatedFor(Set<Task> taskCreatedFor) {
		this.taskCreatedFor = taskCreatedFor;
	}

	public Set<Day> getDays() {
		return days;
	}

	public void setDays(Set<Day> days) {
		this.days = days;
	}

	public Set<Meeting> getMeetings() {
		return meetings;
	}

	public void setMeetings(Set<Meeting> meetings) {
		this.meetings = meetings;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ssoId == null) {
			if (other.ssoId != null)
				return false;
		} else if (!ssoId.equals(other.ssoId))
			return false;
		return true;
	}

	/*
	 * DO-NOT-INCLUDE passwords in toString function.
	 * It is done here just for convenience purpose.
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", ssoId=" + ssoId + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + "]";
	}


	
}
