import { Link } from "react-router-dom";

export default function ToolCard({ tool }) {
    return (
        <div style={styles.card}>
            <div>
                <h3 style={styles.name}>{tool.name}</h3>
                <p style={styles.meta}>{tool.useCase}</p>
                <p style={styles.meta}>
                    {tool.category} · {tool.pricingType}
                </p>
            </div>

            <div style={styles.footer}>
                <span>⭐ {tool.averageRating || "N/A"}</span>
                <Link to={`/tools/${tool.id}`}>
                    <button>View</button>
                </Link>
            </div>
        </div>
    );
}

const styles = {
    card: {
        background: "#020617",
        border: "1px solid #1e293b",
        borderRadius: 16,
        padding: 20,
        display: "flex",
        flexDirection: "column",
        justifyContent: "space-between",
    },
    name: {
        margin: 0,
        fontSize: 18,
        fontWeight: 600,
    },
    meta: {
        color: "#94a3b8",
        fontSize: 14,
        marginTop: 6,
    },
    footer: {
        marginTop: 16,
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
    },
};
