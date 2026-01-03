import { useEffect, useState } from "react";
import api from "../api/api";

export default function AdminTools() {
    const [tools, setTools] = useState([]);
    const [name, setName] = useState("");
    const [category, setCategory] = useState("AI");
    const [pricing, setPricing] = useState("FREE");

    const loadTools = async () => {
        const res = await api.get("/tools"); // correct endpoint
        setTools(res.data);
    };

    useEffect(() => {
        loadTools();
    }, []);

    const createTool = async () => {
        await api.post("/admin/tools", {
            name,
            category,
            pricing
        });

        setName("");
        await loadTools();
    };

    const deleteTool = async (id) => {
        await api.delete(`/admin/tools/${id}`);
        await loadTools();
    };

    return (
        <div style={{ padding: 32 }}>
            <h2>Admin – Tool Management</h2>

            <div>
                <input
                    placeholder="Tool name"
                    value={name}
                    onChange={e => setName(e.target.value)}
                />

                <select value={category} onChange={e => setCategory(e.target.value)}>
                    <option value="AI">AI</option>
                    <option value="DEV">DEV</option>
                </select>

                <select value={pricing} onChange={e => setPricing(e.target.value)}>
                    <option value="FREE">Free</option>
                    <option value="PAID">Paid</option>
                </select>

                <button onClick={createTool}>Create Tool</button>
            </div>

            <hr />

            {tools.map(t => (
                <div key={t.id}>
                    <strong>{t.name}</strong> — {t.category} — {t.pricing}
                    <button onClick={() => deleteTool(t.id)}>Delete</button>
                </div>
            ))}
        </div>
    );
}
