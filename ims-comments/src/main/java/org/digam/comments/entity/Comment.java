package org.digam.comments.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 1, max = 20)
	private String text;
	@NotNull
	private Long byUser;
	private Long forIssue;
	private LocalDateTime created;

	public Comment(Long id, String text, Long byUser, Long forIssue, LocalDateTime created) {
		this.id = id;
		this.text = text;
		this.byUser = byUser;
		this.forIssue = forIssue;
		this.created = created;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getByUser() {
		return byUser;
	}

	public void setByUser(Long byUser) {
		this.byUser = byUser;
	}

	public Long getForIssue() {
		return forIssue;
	}

	public void setForIssue(Long forIssue) {
		this.forIssue = forIssue;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", text=" + text + ", byUser=" + byUser + ", forIssue=" + forIssue + ", created="
				+ created + "]";
	}

}
