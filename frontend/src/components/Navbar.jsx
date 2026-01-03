import { NavLink } from "react-router-dom";

export default function Navbar() {
    return (
        <nav style={styles.nav}>
            <div style={styles.left}>AI Tool Finder</div>

            <div style={styles.links}>
                <NavLink to="/" style={linkStyle}>Home</NavLink>
                <NavLink to="/tools" style={linkStyle}>Tools</NavLink>
                <NavLink to="/admin/tools" style={linkStyle}>Admin Tools</NavLink>
                <NavLink to="/admin/reviews" style={linkStyle}>Admin Reviews</NavLink>
            </div>
        </nav>
    );
}

const linkStyle = ({ isActive }) => ({
    padding: "8px 12px",
    borderRadius: 8,
    background: isActive ? "#1e293b" : "transparent",
    color: "#e5e7eb",
    fontWeight: 500,
});

const styles = {
    nav: {
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        padding: "16px 32px",
        borderBottom: "1px solid #1e293b",
        background: "#020617",
    },
    left: {
        fontSize: 20,
        fontWeight: 700,
        letterSpacing: 0.5,
    },
    links: {
        display: "flex",
        gap: 12,
    },
};
