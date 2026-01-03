export default function FilterBar({ filters, setFilters, onSearch, onClear }) {
    return (
        <div style={styles.container}>
            <input
                placeholder="Search tools..."
                value={filters.q}
                onChange={e => setFilters({ ...filters, q: e.target.value })}
            />

            <select
                value={filters.category}
                onChange={e => setFilters({ ...filters, category: e.target.value })}
            >
                <option value="">All Categories</option>
                <option value="AI">AI</option>
                <option value="Development">Development</option>
                <option value="Marketing">Marketing</option>
                <option value="Productivity">Productivity</option>
                <option value="Design">Design</option>
            </select>

            <select
                value={filters.pricingType}
                onChange={e =>
                    setFilters({ ...filters, pricingType: e.target.value })
                }
            >
                <option value="">All Pricing</option>
                <option value="FREE">Free</option>
                <option value="PAID">Paid</option>
                <option value="SUBSCRIPTION">Subscription</option>
            </select>

            <button onClick={onSearch}>Search</button>
            <button onClick={onClear} style={styles.clear}>Clear</button>
        </div>
    );
}

const styles = {
    container: {
        display: "flex",
        gap: 12,
        marginBottom: 24,
        flexWrap: "wrap",
    },
    clear: {
        background: "#334155",
    },
};
