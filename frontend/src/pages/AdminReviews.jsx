import { useEffect, useState } from "react";
import {
  getReviewsByStatus,
  approveReview,
  rejectReview
} from "../services/api";
import "./AdminReviews.css";

export default function AdminReviews() {
  const [reviews, setReviews] = useState([]);
  const [status, setStatus] = useState("PENDING");
  const [error, setError] = useState(null);

  const loadReviews = () => {
    getReviewsByStatus(status)
      .then(setReviews)
      .catch((err) => setError(err.message));
  };

  useEffect(() => {
    loadReviews();
  }, [status]);

  const handleApprove = async (id) => {
    await approveReview(id);
    loadReviews();
  };

  const handleReject = async (id) => {
    await rejectReview(id);
    loadReviews();
  };

  return (
    <div className="admin-reviews-page">
      <h1 className="page-title">Admin – Review Moderation</h1>
      <p className="page-subtitle">
        Approve or reject user reviews before they affect ratings.
      </p>

      {/* Status filter */}
      <div className="status-bar">
        <label>Status</label>
        <select value={status} onChange={(e) => setStatus(e.target.value)}>
          <option value="PENDING">Pending</option>
          <option value="APPROVED">Approved</option>
          <option value="REJECTED">Rejected</option>
        </select>
      </div>

      {error && <p className="error">{error}</p>}

      {/* Review list */}
      <div className="review-list">
        {reviews.length === 0 && (
          <p className="status">No reviews found.</p>
        )}

        {reviews.map((r) => (
          <div key={r.id} className="review-card">
            <div className="review-header">
              <div className="rating">⭐ {r.rating}</div>
              <span className={`badge ${status.toLowerCase()}`}>
                {status}
              </span>
            </div>

            <p className="comment">{r.comment || "No comment provided."}</p>

            {status === "PENDING" && (
              <div className="actions">
                <button
                  className="approve-btn"
                  onClick={() => handleApprove(r.id)}
                >
                  Approve
                </button>
                <button
                  className="reject-btn"
                  onClick={() => handleReject(r.id)}
                >
                  Reject
                </button>
              </div>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}
