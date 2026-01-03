import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";

import Home from "./pages/Home";
import ToolList from "./pages/ToolList";
import ToolDetails from "./pages/ToolDetails";
import SubmitReview from "./pages/SubmitReview";
import AdminTools from "./pages/AdminTools";
import AdminReviews from "./pages/AdminReviews";
import NotFound from "./pages/NotFound";

export default function App() {
    return (
        <BrowserRouter>
            <Navbar />

            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/tools" element={<ToolList />} />
                <Route path="/tools/:id" element={<ToolDetails />} />
                <Route path="/tools/:id/review" element={<SubmitReview />} />
                <Route path="/admin/tools" element={<AdminTools />} />
                <Route path="/admin/reviews" element={<AdminReviews />} />
                <Route path="*" element={<NotFound />} />
            </Routes>
        </BrowserRouter>
    );
}
