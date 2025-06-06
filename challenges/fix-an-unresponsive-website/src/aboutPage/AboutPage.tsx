import React, { useState, Suspense } from "react";
import Credits from "../credits/Credits";

const AboutPage = () => {
    const [showCredits, setShowCredits] = useState(false);

    const handleClick = () => {
        setShowCredits(!showCredits);
    }

    return (
        <div>
            <h1>About Page</h1>
            <p>This is the payments app</p>
            <button onClick={handleClick}>
                {showCredits ? "hide" : "show"} credits
            </button>
            <Suspense fallback={<p>Loading credits...</p>}>
                <div style={{ position: 'relative' }}>
                    {showCredits && <Credits />}
                </div>
            </Suspense>
        </div>
    );
}

export default AboutPage;