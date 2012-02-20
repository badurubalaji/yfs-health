package com.varun.yfs.server.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "users")
public class User implements Serializable
{
	private static final long serialVersionUID = 3137505216329922435L;

	@Id
	@GeneratedValue
	@Column(name = "userId")
	private long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	@Column(nullable = true)
	private String role;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "User_ChapterPermissions", joinColumns = { @JoinColumn(name = "userId") },
			inverseJoinColumns = { @JoinColumn(name = "chapterPermId") })
	@Column(nullable = true)
	@Fetch(FetchMode.SELECT)
	private List<UserChapterPermissions> chapterPermissions;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "User_ProjectPermissions", joinColumns = { @JoinColumn(name = "userId") },
			inverseJoinColumns = { @JoinColumn(name = "projPermId") })
	@Column(nullable = true)
	@Fetch(FetchMode.SELECT)
	private List<UserProjectPermissions> projectPermissions;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "User_ClinicPermissions", joinColumns = { @JoinColumn(name = "userId") },
			inverseJoinColumns = { @JoinColumn(name = "clinicPermId") })
	@Column(nullable = true)
	@Fetch(FetchMode.SELECT)
	private List<UserClinicPermissions> clinicPermissions;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "User_ReportPermissions", joinColumns = { @JoinColumn(name = "userId") },
			inverseJoinColumns = { @JoinColumn(name = "reportPermId") })
	@Column(nullable = true)
	@Fetch(FetchMode.SELECT)
	private List<UserReportPermissions> reportPermissions;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "User_EntityPermissions", joinColumns = { @JoinColumn(name = "userId") },
			inverseJoinColumns = { @JoinColumn(name = "entityPermId") })
	@Column(nullable = true)
	@Fetch(FetchMode.SELECT)
	private List<UserEntityPermissions> entityPermissions;

	@Column(nullable = false)
	private String deleted;

	public User()
	{
		setDeleted("N");
	}

	public User(String name, String pass)
	{
		setName(name);
		setPassword(pass);
		setDeleted("N");
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public final void setName(String name)
	{
		this.name = name;
	}

	public String getDeleted()
	{
		return deleted;
	}

	public final void setDeleted(String deleted)
	{
		this.deleted = deleted;
	}

	public final void setPassword(String password)
	{
		this.password = password;
	}

	public String getPassword()
	{
		return password;
	}

	public void setChapterPermissions(List<UserChapterPermissions> chapterName)
	{
		this.chapterPermissions = chapterName;
	}

	public List<UserChapterPermissions> getChapterPermissions()
	{
		return chapterPermissions;
	}

	public void setProjectPermissions(List<UserProjectPermissions> project)
	{
		this.projectPermissions = project;
	}

	public List<UserProjectPermissions> getProjectPermissions()
	{
		return projectPermissions;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public String getRole()
	{
		return role;
	}

	public void setClinicPermissions(List<UserClinicPermissions> clinicPermissions)
	{
		this.clinicPermissions = clinicPermissions;
	}

	public List<UserClinicPermissions> getClinicPermissions()
	{
		return clinicPermissions;
	}

	public void setReportPermissions(List<UserReportPermissions> reportPermissions)
	{
		this.reportPermissions = reportPermissions;
	}

	public List<UserReportPermissions> getReportPermissions()
	{
		return reportPermissions;
	}

	public void setEntityPermissions(List<UserEntityPermissions> entityPermissions)
	{
		this.entityPermissions = entityPermissions;
	}

	public List<UserEntityPermissions> getEntityPermissions()
	{
		return entityPermissions;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (!name.equalsIgnoreCase(other.name))
			return false;
		return true;
	}

}