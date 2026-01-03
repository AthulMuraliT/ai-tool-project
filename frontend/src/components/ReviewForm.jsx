import { useState } from "react";
import api from "../api/api";

export default function ReviewForm({ toolId, onSuccess }) {
    const [rating, setRating] = useState(5);
    const [comment, setComment] = useState("");

    const submit = async () => {
        await api.post("/reviews", {
            toolId,
            rating,
            comment,
        });

        setComment("");
        setRating(5);
        onSuccess?.();
    };

    return (
        <div>
            <h3>Submit Review</h3>

            <input
                type="number"
                min="1"
                max="5"
                value={rating}
                onChange={e => setRating(Number(e.target.value))}
            />

            <textarea
                placeholder="Your review"
                value={comment}
                onChange={e => setComment(e.target.value)}
            />

            <button onClick={submit}>Submit</button>
        </div>
    );
}
