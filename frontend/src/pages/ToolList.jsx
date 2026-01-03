import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getTools } from "../services/api";
import "./ToolList.css";

export default function ToolList() {
  const [tools, setTools] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const [category, setCategory] = useState("");
  const [pricing, setPricing] = useState("");
  const [rating, setRating] = useState("");

  const [searchInput, setSearchInput] = useState("");
  const [search, setSearch] = useState("");

  useEffect(() => {
    const timer = setTimeout(() => {
      setSearch(searchInput);
    }, 400);
    return () => clearTimeout(timer);
  }, [searchInput]);

  useEffect(() => {
    setLoading(true);
    setError(null);

    const params = new URLSearchParams();
    if (category) params.append("category", category);
    if (pricing) params.append("pricing", pricing);
    if (rating) params.append("rating", rating);
    if (search) params.append("q", search);

    const query = params.toString() ? `?${params.toString()}` : "";

    getTools(query)
      .then(setTools)
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false));
  }, [category, pricing, rating, search]);

  return (
    <div className="tool-page">
      <h1 className="page-title">Explore AI Tools</h1>
      <p className="page-subtitle">
        Discover, filter, and review AI tools curated for productivity.
      </p>

      {/* Filters */}
      <div className="filter-bar">
        <input
          className="search-input"
          placeholder="Search by name or use case..."
          value={searchInput}
          onChange={(e) => setSearchInput(e.target.value)}
        />

        <select value={category} onChange={(e) => setCategory(e.target.value)}>
          <option value="">All Categories</option>
          <option value="AI">AI</option>
          <option value="Development">Development</option>
          <option value="Marketing">Marketing</option>
          <option value="Productivity">Productivity</option>
        </select>

        <select value={pricing} onChange={(e) => setPricing(e.target.value)}>
          <option value="">All Pricing</option>
          <option value="FREE">Free</option>
          <option value="PAID">Paid</option>
          <option value="SUBSCRIPTION">Subscription</option>
        </select>

        <select value={rating} onChange={(e) => setRating(e.target.value)}>
          <option value="">Any Rating</option>
          <option value=">=4">⭐ ≥ 4</option>
          <option value=">=3">⭐ ≥ 3</option>
          <option value="=5">⭐ 5</option>
        </select>
      </div>

      {/* States */}
      {loading && <p className="status">Loading tools...</p>}
      {error && <p className="error">{error}</p>}
      {!loading && tools.length === 0 && <p className="status">No tools found.</p>}

      {/* Cards */}
      <div className="tool-grid">
        {tools.map((tool) => (
          <Link key={tool.id} to={`/tools/${tool.id}`} className="tool-card">
            <h3>{tool.name}</h3>
            <p className="use-case">{tool.useCase}</p>

            <div className="meta">
              <span className="badge">{tool.category}</span>
              <span className="badge pricing">{tool.pricingType}</span>
            </div>

            <div className="rating">⭐ {tool.averageRating}</div>
          </Link>
        ))}
      </div>
    </div>
  );
}
