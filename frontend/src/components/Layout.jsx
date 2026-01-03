import { Link } from "react-router-dom";
import "./Layout.css";

export default function Layout({ children }) {
  return (
    <>
      <header className="navbar">
        <div className="logo">
          <Link to="/">AI Tool Finder</Link>
        </div>

        <nav className="nav-links">
          <Link to="/tools">Browse Tools</Link>
          <Link to="/admin/tools">Tool Management</Link>
          <Link to="/admin/reviews">Review Moderation</Link>
        </nav>

        <div className="nav-actions">
          <Link to="/tools" className="nav-login">Explore</Link>
          <Link to="/tools" className="nav-cta">Get Started</Link>
        </div>
      </header>

      <main className="main-content">
        {children}
      </main>
    </>
  );
}
