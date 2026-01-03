import { useEffect, useState } from "react";
import api from "../api/api";
import ToolCard from "../components/ToolCard";
import FilterBar from "../components/FilterBar";

export default function ToolList() {
    const [tools, setTools] = useState([]);
    const [filters, setFilters] = useState({
        q: "",
        category: "",
        pricingType: "",
    });
    const [searched, setSearched] = useState(false);

    useEffect(() => {
        loadAllTools();
    }, []);

    const loadAllTools = async () => {
        const res = await api.get("/tools");
        setTools(res.data);
        setSearched(false);
    };

    const searchTools = async () => {
        const params = {
            q: filters.q || undefined,
            category: filters.category || undefined,
            pricing: filters.pricingType || undefined, // 👈 backend alias
        };

        const res = await api.get("/tools", { params });
        setTools(res.data);
        setSearched(true);
    };

    const clearFilters = async () => {
        setFilters({ q: "", category: "", pricingType: "" });
        await loadAllTools();
    };

    return (
        <div style={{ padding: 32 }}>
            <FilterBar
                filters={filters}
                setFilters={setFilters}
                onSearch={searchTools}
                onClear={clearFilters}
            />

            {searched && tools.length === 0 && (
                <p>No tools found matching your search.</p>
            )}

            <div style={grid}>
                {tools.map(t => (
                    <ToolCard key={t.id} tool={t} />
                ))}
            </div>
        </div>
    );
}

const grid = {
    display: "grid",
    gridTemplateColumns: "repeat(auto-fill, minmax(260px, 1fr))",
    gap: 20,
};


