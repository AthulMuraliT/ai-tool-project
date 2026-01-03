import { useEffect, useState } from "react";
import {
  getTools,
  createTool,
  updateTool,
  deleteTool
} from "../services/api";
import "./AdminTools.css";

export default function AdminTools() {
  const [tools, setTools] = useState([]);
  const [form, setForm] = useState({
    name: "",
    useCase: "",
    category: "",
    pricingType: "FREE"
  });
  const [editingId, setEditingId] = useState(null);

  const loadTools = () => {
    getTools().then(setTools);
  };

  useEffect(() => {
    loadTools();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (editingId) {
      await updateTool(editingId, form);
    } else {
      await createTool(form);
    }

    setForm({ name: "", useCase: "", category: "", pricingType: "FREE" });
    setEditingId(null);
    loadTools();
  };

  const startEdit = (tool) => {
    setEditingId(tool.id);
    setForm({
      name: tool.name,
      useCase: tool.useCase,
      category: tool.category,
      pricingType: tool.pricingType
    });
  };

  const handleDelete = async (id) => {
    await deleteTool(id);
    loadTools();
  };

  return (
    <div className="admin-page">
      <h1 className="page-title">Admin – Tool Management</h1>
      <p className="page-subtitle">
        Add, edit, and manage AI tools available on the platform.
      </p>

      {/* Form */}
      <div className="admin-card">
        <h2>{editingId ? "Edit Tool" : "Add New Tool"}</h2>

        <form onSubmit={handleSubmit} className="tool-form">
          <input
            placeholder="Tool Name"
            value={form.name}
            onChange={(e) => setForm({ ...form, name: e.target.value })}
            required
          />

          <input
            placeholder="Use Case"
            value={form.useCase}
            onChange={(e) => setForm({ ...form, useCase: e.target.value })}
            required
          />

          <input
            placeholder="Category"
            value={form.category}
            onChange={(e) => setForm({ ...form, category: e.target.value })}
            required
          />

          <select
            value={form.pricingType}
            onChange={(e) =>
              setForm({ ...form, pricingType: e.target.value })
            }
          >
            <option value="FREE">FREE</option>
            <option value="PAID">PAID</option>
            <option value="SUBSCRIPTION">SUBSCRIPTION</option>
          </select>

          <button type="submit" className="primary-btn">
            {editingId ? "Update Tool" : "Add Tool"}
          </button>
        </form>
      </div>

      {/* Tool list */}
      <div className="tool-table">
        <h2>Existing Tools</h2>

        {tools.map((tool) => (
          <div key={tool.id} className="tool-row">
            <div>
              <strong>{tool.name}</strong>
              <p className="muted">{tool.useCase}</p>
            </div>

            <div className="badges">
              <span className="badge">{tool.category}</span>
              <span className="badge pricing">{tool.pricingType}</span>
            </div>

            <div className="actions">
              <button
                className="edit-btn"
                onClick={() => startEdit(tool)}
              >
                Edit
              </button>
              <button
                className="delete-btn"
                onClick={() => handleDelete(tool.id)}
              >
                Delete
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
