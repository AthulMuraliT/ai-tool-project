import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import api from "../api/api";
import ReviewForm from "../components/ReviewForm";

export default function ToolDetails() {
    const { id } = useParams();
    const [tool, setTool] = useState(null);

    useEffect(() => {
        api.get(`/tools/${id}`).then(res => setTool(res.data));
    }, [id]);

    if (!tool) return <p>Loading...</p>;

    return (
        <div>
            <h2>{tool.name}</h2>
            <p>{tool.description}</p>
            <p>Rating: {tool.averageRating ?? "N/A"}</p>

            <ReviewForm toolId={id} onSuccess={() => alert("Review submitted")} />
        </div>
    );
}
