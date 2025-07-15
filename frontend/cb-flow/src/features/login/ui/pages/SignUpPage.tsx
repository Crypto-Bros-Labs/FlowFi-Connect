import React from "react";
import SignUpPanel from "../components/SignUpPanel";

const SignUpPage: React.FC = () => {
    return (
        <div className="flex items-center justify-center h-[calc(100vh-64px)] md:h-[calc(120vh-80px)] bg-[#F5F7FA] w-full">
            <div className="w-[80%] md:w-130">
                <SignUpPanel isFlow={true} />
            </div>
        </div>
    );
};

export default SignUpPage;