package com.isep.acme.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idRating;

	@Version
	private long version;

	@Column(nullable = false)
	private Double rate;

	protected Rating() {
	}

	public Rating(Long idRating, long version, Double rate) {
		this.idRating = Objects.requireNonNull(idRating);
		this.version = Objects.requireNonNull(version);
		setRate(rate);
	}

	public Rating(Double rate) {
		setRate(rate);
	}

}
