import { Link } from "react-router-dom";

export default function Home() {
  return (
    <div style={{ padding: "20px" }}>
      <h1>AI Tool Finder</h1>
      <p>Select where you want to go:</p>

      <ul>
        <li>
          <Link to="/tools">🔍 Browse Tools</Link>
        </li>
        <li>
          <Link to="/admin/tools">🛠 Admin – Tool Management</Link>
        </li>
        <li>
          <Link to="/admin/reviews">🧾 Admin – Review Moderation</Link>
        </li>
      </ul>
    </div>
  );
}
