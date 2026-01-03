import { useEffect, useState } from "react";
import api from "../api/api";

export default function AdminReviews() {
    const [reviews, setReviews] = useState([]);

    const loadReviews = async () => {
        const res = await api.get("/admin/reviews", {
            params: { status: "PENDING" }
        });
        setReviews(res.data);
    };

    useEffect(() => {
        loadReviews();
    }, []);

    const approve = async (id) => {
        await api.put(`/admin/reviews/${id}/approve`);
        await loadReviews();
    };

    const reject = async (id) => {
        await api.put(`/admin/reviews/${id}/reject`);
        await loadReviews();
    };

    return (
        <div style={{ padding: 32 }}>
            <h2>Pending Reviews</h2>

            {reviews.length === 0 && <p>No pending reviews</p>}

            {reviews.map(r => (
                <div key={r.id}>
                    <p>{r.comment}</p>
                    <button onClick={() => approve(r.id)}>Approve</button>
                    <button onClick={() => reject(r.id)}>Reject</button>
                </div>
            ))}
        </div>
    );
}
