import { useState, useEffect } from "react";
import Credits from "../credits/Credits";

const AboutPage = () => {
    const [showCredits, setShowCredits] = useState(false);
    const [renderCredits, setRenderCredits] = useState(false);

    const handleClick = () => {
        if (showCredits) {
            // Hide immediately
            setShowCredits(false);
            setRenderCredits(false);
        } else {
            // Show flag immediately (for UI state)
            setShowCredits(true);
            // Defer actual rendering to next tick
            setTimeout(() => {
                setRenderCredits(true);
            }, 0);
        }
    };

    // Reset render flag when showCredits becomes false
    useEffect(() => {
        if (!showCredits) {
            setRenderCredits(false);
        }
    }, [showCredits]);

    return (
        <div>
            <h1>About Page</h1>
            <p>This is the payments app</p>
            <button onClick={handleClick}>{showCredits ? "hide" : "show"} credits</button>
            {showCredits && renderCredits && <Credits />}
        </div>
    );
};

export default AboutPage;