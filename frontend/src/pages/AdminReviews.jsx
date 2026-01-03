import { useEffect, useState } from "react";
import api from "../api/api";
import "./AdminReviews.css";

export default function AdminReviews() {
    const [reviews, setReviews] = useState([]);

    const loadReviews = async () => {
        const res = await api.get("/admin/reviews", {
            params: { status: "PENDING" },
        });
        setReviews(res.data);
    };

    useEffect(() => {
        loadReviews();
    }, []);

    const approve = async (id) => {
        await api.put(`/admin/reviews/${id}/approve`);
        loadReviews();
    };

    const reject = async (id) => {
        await api.put(`/admin/reviews/${id}/reject`);
        loadReviews();
    };

    return (
        <div className="reviews-page">
            <h2 className="reviews-title">Pending Reviews</h2>

            {reviews.length === 0 && (
                <p className="empty-text">No pending reviews 🎉</p>
            )}

            <div className="reviews-grid">
                {reviews.map((r) => (
                    <div key={r.id} className="review-card">
                        <p className="review-text">“{r.comment}”</p>

                        <div className="review-actions">
                            <button
                                className="approve-btn"
                                onClick={() => approve(r.id)}
                            >
                                Approve
                            </button>
                            <button
                                className="reject-btn"
                                onClick={() => reject(r.id)}
                            >
                                Reject
                            </button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}
