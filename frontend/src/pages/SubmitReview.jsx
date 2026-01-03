import { useParams } from "react-router-dom";
import { useState } from "react";
import api from "../api/api";

export default function SubmitReview() {
    const { id } = useParams();
    const [comment, setComment] = useState("");
    const [rating, setRating] = useState(5);

    const submit = async () => {
        await api.post("/reviews", {
            toolId: id,
            rating,
            comment
        });

        alert("Review submitted for approval");
        setComment("");
    };

    return (
        <div style={{ padding: 32 }}>
            <h2>Submit Review</h2>

            <input
                type="number"
                min="1"
                max="5"
                value={rating}
                onChange={e => setRating(Number(e.target.value))}
            />

            <textarea
                value={comment}
                onChange={e => setComment(e.target.value)}
            />

            <button onClick={submit}>Submit</button>
        </div>
    );
}
