package com.mi.Gestion_Stock.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.persistence.OneToMany;

@Entity
public class Provider {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;
    
    @NotBlank(message = "Address is mandatory")
    @Column(name = "address")
    private String address;
    
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    private String email;
    
    
    @NotBlank(message = "logo is mandatory")
    @Column(name = "logo")
    private String logo;
    
    @NotBlank(message = "phone is mandatory")
    @Column(name = "phone")
    private String phone;
    
    public Provider() {}

    public Provider(String name, String address, String email,String logo, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.logo = logo;
        this.phone = phone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
    
    
   /* @OneToMany(cascade=CascadeType.ALL, mappedBy = "provider")
    private List<Article> articles;

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}*/

}
