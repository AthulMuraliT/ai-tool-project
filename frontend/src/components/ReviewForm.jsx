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
        <div style={styles.wrapper}>
            <h3 style={{ marginBottom: 16 }}>Submit Review</h3>

            <div style={styles.row}>
                <input
                    type="number"
                    min="1"
                    max="5"
                    value={rating}
                    onChange={e => setRating(Number(e.target.value))}
                    style={styles.rating}
                />

                <textarea
                    placeholder="Your review"
                    value={comment}
                    onChange={e => setComment(e.target.value)}
                    style={styles.textarea}
                />

                <button onClick={submit} style={styles.button}>
                    Submit
                </button>
            </div>
        </div>
    );
}

const styles = {
    wrapper: {
        marginTop: 32,
        marginLeft: 16,
    },
    row: {
        display: "flex",
        alignItems: "center",
        gap: 16,
    },
    rating: {
        width: 60,
        height: 42,
        textAlign: "center",
        borderRadius: 8,
        border: "1px solid #334155",
        background: "#020617",
        color: "#e5e7eb",
    },
    textarea: {
        width: 280,
        height: 42,
        resize: "none",
        padding: "8px 12px",
        borderRadius: 8,
        border: "1px solid #334155",
        background: "#020617",
        color: "#e5e7eb",
    },
    button: {
        height: 42,
        padding: "0 20px",
        borderRadius: 8,
        background: "#2563eb",
        color: "#fff",
        border: "none",
        cursor: "pointer",
    },
};
