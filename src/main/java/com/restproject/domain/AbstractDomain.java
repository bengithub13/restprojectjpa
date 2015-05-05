package com.restproject.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractDomain implements Serializable {
	@Id
	@Column(name = "ID")
	private Long id;
	public static final String GENERATED_VALUE_STRATEGY="seq";
	private static final long serialVersionUID=2l;
	protected AbstractDomain() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}

	@Override
	public boolean equals(Object o) {
		if (o != null) {
			if (this == o) {
				return true;
			}
			if (this.getClass() == o.getClass()) {
				if (this.getId() == ((AbstractDomain) o).getId()) {
					return true;
				}
			}
		}
		return false;
	}
}