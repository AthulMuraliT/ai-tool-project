const BASE_URL = "http://localhost:8080/api";

export async function getTools(params = "") {
  const res = await fetch(`${BASE_URL}/tools${params}`);
  if (!res.ok) {
    throw new Error("Failed to fetch tools");
  }
  return res.json();
}
