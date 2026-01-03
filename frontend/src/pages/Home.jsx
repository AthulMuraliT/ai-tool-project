export default function Home() {
    return (
        <div style={styles.wrapper}>
            <h1 style={styles.title}>Discover AI Tools That Matter</h1>
            <p style={styles.subtitle}>
                Explore, review, and manage AI tools with community-driven feedback.
            </p>

            <div style={styles.actions}>
                <a href="/tools">
                    <button>Browse Tools</button>
                </a>
            </div>
        </div>
    );
}

const styles = {
    wrapper: {
        padding: "80px 32px",
        maxWidth: 900,
    },
    title: {
        fontSize: 48,
        fontWeight: 800,
        lineHeight: 1.1,
        marginBottom: 16,
    },
    subtitle: {
        fontSize: 18,
        color: "#94a3b8",
        maxWidth: 600,
        marginBottom: 32,
    },
    actions: {
        display: "flex",
        gap: 16,
    },
};
