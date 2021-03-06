package org.digam.comments.boundary;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.digam.comments.control.FindUserName;
import org.digam.comments.entity.Comment;
import org.digam.comments.entity.CommentInfo;

@Stateless
public class CommentsService {

	@PersistenceContext
	private EntityManager em;

	private FindUserName findUserName;
	
	@Inject
	public void setFindUserName(FindUserName userName) {
		this.findUserName = userName;
	}

	public FindUserName getFindUserName() {
		return this.findUserName;
	}

	public Set<CommentInfo> getAll(Long issueId) {
		List<Comment> list = em.createQuery("FROM Comment c WHERE c.forIssue = :id", Comment.class)
				.setParameter("id", issueId).getResultList();

		return list.parallelStream().map(CommentInfo::new).map(this::updateName).collect(Collectors.toSet());
	}

	public Optional<Comment> get(Long id) {
		Comment found = em.find(Comment.class, id);
		return found != null ? Optional.of(found) : Optional.empty();
	}

	public void add(Comment newComment) {
		em.persist(newComment);
	}

	public void remove(Long id) {
		Query query = em.createQuery("DELETE FROM Comment i WHERE i.id = :id");
		query.setParameter("id", id).executeUpdate();
	}

	public CommentInfo updateName(CommentInfo info) {
		String name = this.findUserName.getUserName(info.getComment().getByUser());
		info.setByUserName(name);
		return info;
	}

}
