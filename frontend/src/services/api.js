const BASE_URL = "http://localhost:8080/api";

export async function getTools(params = "") {
  const res = await fetch(`${BASE_URL}/tools${params}`);
  if (!res.ok) {
    throw new Error("Failed to fetch tools");
  }
  return res.json();
}

export async function getToolById(id) {
  const res = await fetch(`http://localhost:8080/api/tools/${id}`);
  if (!res.ok) {
    throw new Error("Failed to fetch tool");
  }
  return res.json();
}


export async function submitReview(review) {
  const res = await fetch("http://localhost:8080/api/reviews", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(review)
  });

  if (!res.ok) {
    throw new Error("Failed to submit review");
  }

  return res.json();
}

export async function getReviewsByStatus(status = "PENDING") {
  const res = await fetch(`http://localhost:8080/api/admin/reviews?status=${status}`);
  if (!res.ok) {
    throw new Error("Failed to fetch reviews");
  }
  return res.json();
}

export async function approveReview(id) {
  const res = await fetch(`http://localhost:8080/api/admin/reviews/${id}/approve`, {
    method: "PUT"
  });
  if (!res.ok) {
    throw new Error("Failed to approve review");
  }
  return res.json();
}

export async function rejectReview(id) {
  const res = await fetch(`http://localhost:8080/api/admin/reviews/${id}/reject`, {
    method: "PUT"
  });
  if (!res.ok) {
    throw new Error("Failed to reject review");
  }
  return res.json();
}

export async function createTool(tool) {
  const res = await fetch("http://localhost:8080/api/admin/tools", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(tool)
  });
  if (!res.ok) throw new Error("Failed to create tool");
  return res.json();
}

export async function updateTool(id, tool) {
  const res = await fetch(`http://localhost:8080/api/admin/tools/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(tool)
  });
  if (!res.ok) throw new Error("Failed to update tool");
  return res.json();
}

export async function deleteTool(id) {
  const res = await fetch(`http://localhost:8080/api/admin/tools/${id}`, {
    method: "DELETE"
  });
  if (!res.ok) throw new Error("Failed to delete tool");
}


