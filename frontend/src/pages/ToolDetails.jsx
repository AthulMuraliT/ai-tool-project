import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import api from "../api/api";
import ReviewForm from "../components/ReviewForm";
import "./ToolDetails.css";

export default function ToolDetails() {
    const { id } = useParams();
    const [tool, setTool] = useState(null);

    useEffect(() => {
        api.get(`/tools/${id}`).then(res => setTool(res.data));
    }, [id]);

    if (!tool) return <p className="loading">Loading...</p>;

    return (
        <div className="tool-page">
            {/* TOOL HEADER */}
            <div className="tool-header">
                <h1 className="tool-name">{tool.name}</h1>

                <div className="tool-meta">
          <span className="rating">
            ⭐ {tool.averageRating ?? "N/A"}
          </span>
                    <span className="dot">•</span>
                    <span>{tool.category}</span>
                    <span className="dot">•</span>
                    <span>{tool.pricingType}</span>
                </div>

                {tool.useCase && (
                    <p className="tool-description">
                        {tool.useCase}
                    </p>
                )}
            </div>

            {/* REVIEW SECTION */}
            <div className="review-section">
                <ReviewForm
                    toolId={id}
                    onSuccess={() => alert("Review submitted for approval")}
                />
            </div>
        </div>
    );
}
