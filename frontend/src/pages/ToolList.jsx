import { useEffect, useState } from "react";
import { getTools } from "../services/api";

export default function ToolList() {
  const [tools, setTools] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    getTools()
      .then(setTools)
      .catch(err => setError(err.message))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <p>Loading tools...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div style={{ padding: "20px" }}>
      <h2>Tool Listing</h2>

      <ul>
        {tools.map(tool => (
          <li key={tool.id}>
            {tool.name} — ⭐ {tool.averageRating}
          </li>
        ))}
      </ul>
    </div>
  );
}
