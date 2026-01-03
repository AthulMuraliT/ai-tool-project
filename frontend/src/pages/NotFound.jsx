import { Link } from "react-router-dom";
import "./NotFound.css";

export default function NotFound() {
  return (
    <div className="notfound-page">
      <div className="notfound-card">
        <h1>404</h1>
        <h2>Page not found</h2>
        <p>
          Oops! The page you’re looking for doesn’t exist or was moved.
        </p>

        <Link to="/" className="primary-btn">
          ⬅ Back to Home
        </Link>
      </div>
    </div>
  );
}
