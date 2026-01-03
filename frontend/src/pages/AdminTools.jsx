import { useEffect, useState } from "react";
import api from "../api/api";
import "./AdminTools.css";

export default function AdminTools() {
    const [tools, setTools] = useState([]);
    const [name, setName] = useState("");
    const [category, setCategory] = useState("AI");
    const [pricingType, setPricingType] = useState("FREE");

    const loadTools = async () => {
        const res = await api.get("/tools");
        setTools(res.data);
    };

    useEffect(() => {
        loadTools();
    }, []);

    const createTool = async () => {
        if (!name.trim()) return;

        await api.post("/admin/tools", {
            name,
            category,
            pricingType,
            useCase: "Admin created tool",
        });

        setName("");
        loadTools();
    };

    const deleteTool = async (id) => {
        await api.delete(`/admin/tools/${id}`);
        loadTools();
    };

    return (
        <div className="admin-page">
            <h2 className="admin-title">Admin – Tool Management</h2>

            {/* CREATE TOOL */}
            <div className="card">
                <h3 className="card-title">Create New Tool</h3>

                <div className="form-row">
                    <input
                        className="input"
                        placeholder="Tool name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                    />

                    <select
                        className="select"
                        value={category}
                        onChange={(e) => setCategory(e.target.value)}
                    >
                        <option value="AI">AI</option>
                        <option value="Development">Development</option>
                        <option value="Marketing">Marketing</option>
                        <option value="Productivity">Productivity</option>
                        <option value="Design">Design</option>
                    </select>

                    <select
                        className="select"
                        value={pricingType}
                        onChange={(e) => setPricingType(e.target.value)}
                    >
                        <option value="FREE">Free</option>
                        <option value="PAID">Paid</option>
                        <option value="SUBSCRIPTION">Subscription</option>
                    </select>

                    <button className="primary-btn" onClick={createTool}>
                        Create Tool
                    </button>
                </div>
            </div>

            {/* TOOL LIST */}
            <div className="table">
                <div className="row header-row">
                    <span>Name</span>
                    <span>Category</span>
                    <span>Action</span>
                </div>

                {tools.map((tool) => (
                    <div key={tool.id} className="row">
                        <span className="tool-name">{tool.name}</span>
                        <span className="tool-category">{tool.category}</span>
                        <button
                            className="delete-btn"
                            onClick={() => deleteTool(tool.id)}
                        >
                            Delete
                        </button>
                    </div>
                ))}
            </div>
        </div>
    );
}
