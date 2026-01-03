import { Link } from "react-router-dom";
import "./Home.css";

export default function Home() {
  return (
    <div className="home">

      {/* Hero */}
      <section className="hero">
        <div className="hero-text">
          <h1>
            Discover the <span>Best AI Tools</span> Instantly
          </h1>

          <p>
            Search, filter, and review AI tools with real user feedback and
            admin-verified ratings.
          </p>

          <div className="hero-actions">
            <Link to="/tools" className="primary-btn">
              🔍 Browse Tools
            </Link>
          </div>

          {/* Admin Quick Actions */}
          <div className="admin-actions">
            <Link to="/admin/tools" className="admin-btn">
              🛠 Tool Management
            </Link>
            <Link to="/admin/reviews" className="admin-btn outline">
              🧾 Review Moderation
            </Link>
          </div>
        </div>

        <div className="hero-image">
          <img
            src="https://cdni.iconscout.com/illustration/premium/thumb/artificial-intelligence-server-data-management-11340634-9145341.png?f=webp"
            alt="AI tools illustration"
          />
        </div>
      </section>

      {/* Features */}
      <section className="features" id="features">
        <div className="feature-card">
          <h3>⚡ Advanced Filters</h3>
          <p>Category, pricing, rating — all at once.</p>
        </div>

        <div className="feature-card">
          <h3>⭐ Trusted Reviews</h3>
          <p>Only approved reviews affect ratings.</p>
        </div>

        <div className="feature-card">
          <h3>🛡 Admin Control</h3>
          <p>Moderate tools and reviews securely.</p>
        </div>
      </section>

      <footer className="footer">
        <p>© 2026 AI Tool Finder</p>
      </footer>
    </div>
  );
}
