import React from "react";
import OtpPanel from "../components/OtpPanel";

const OtpPage: React.FC = () => {
    return (
        <div className="flex items-center justify-center h-[calc(100vh-64px)] md:h-[calc(100vh-80px)] bg-[#F5F7FA] w-full">
            <div className="w-[80%] md:w-130">
                <OtpPanel isFlow={true} />
            </div>
        </div>
    );
};

export default OtpPage;