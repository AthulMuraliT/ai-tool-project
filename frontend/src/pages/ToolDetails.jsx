import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { getToolById } from "../services/api";
import "./ToolDetails.css";

export default function ToolDetails() {
  const { id } = useParams();
  const [tool, setTool] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    getToolById(id)
      .then(setTool)
      .catch((err) => setError(err.message));
  }, [id]);

  if (error) return <p className="error">Error: {error}</p>;
  if (!tool) return <p className="status">Loading tool...</p>;

  return (
    <div className="tool-details-page">
      <Link to="/tools" className="back-link">← Back to tools</Link>

      <div className="tool-details-card">
        <div className="header">
          <h1>{tool.name}</h1>
          <div className="rating">⭐ {tool.averageRating}</div>
        </div>

        <p className="use-case">{tool.useCase}</p>

        <div className="meta">
          <span className="badge">{tool.category}</span>
          <span className="badge pricing">{tool.pricingType}</span>
        </div>

        <div className="divider" />

        <div className="info">
          <div>
            <h4>Category</h4>
            <p>{tool.category}</p>
          </div>

          <div>
            <h4>Pricing Model</h4>
            <p>{tool.pricingType}</p>
          </div>

          <div>
            <h4>Average Rating</h4>
            <p>⭐ {tool.averageRating}</p>
          </div>
        </div>

        <div className="actions">
          <Link to={`/tools/${id}/review`} className="primary-btn">
            ✍️ Submit a Review
          </Link>
        </div>
      </div>
    </div>
  );
}
