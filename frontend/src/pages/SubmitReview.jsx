import { useState } from "react";
import { useParams, useNavigate, Link } from "react-router-dom";
import { submitReview } from "../services/api";
import "./SubmitReview.css";

export default function SubmitReview() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [rating, setRating] = useState(5);
  const [comment, setComment] = useState("");
  const [message, setMessage] = useState(null);
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);

    try {
      await submitReview({
        toolId: Number(id),
        rating,
        comment
      });
      setMessage("Review submitted and pending approval!");
      setTimeout(() => navigate(`/tools/${id}`), 1500);
    } catch (err) {
      setError(err.message);
    }
  };

  return (
    <div className="review-page">
      <Link to={`/tools/${id}`} className="back-link">
        ← Back to tool
      </Link>

      <div className="review-card">
        <h1>Submit Your Review</h1>
        <p className="subtitle">
          Share your experience. Reviews are published after admin approval.
        </p>

        <form onSubmit={handleSubmit} className="review-form">
          {/* Rating */}
          <div className="form-group">
            <label>Rating</label>
            <select
              value={rating}
              onChange={(e) => setRating(Number(e.target.value))}
            >
              {[1, 2, 3, 4, 5].map((r) => (
                <option key={r} value={r}>
                  {r} Star{r > 1 && "s"}
                </option>
              ))}
            </select>
          </div>

          {/* Comment */}
          <div className="form-group">
            <label>Comment</label>
            <textarea
              placeholder="Write your honest feedback..."
              value={comment}
              onChange={(e) => setComment(e.target.value)}
            />
          </div>

          <button type="submit" className="primary-btn">
            🚀 Submit Review
          </button>
        </form>

        {message && <p className="success">{message}</p>}
        {error && <p className="error">{error}</p>}
      </div>
    </div>
  );
}
