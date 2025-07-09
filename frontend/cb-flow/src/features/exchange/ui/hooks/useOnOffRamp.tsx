import { useState } from "react";

export const useOnOffRamp = () => {
    //switch
    const [activeTab, setActiveTab] = useState("onRamp");

    const tabs = [
        { id: "onRamp", title: "Comprar" },
        { id: "offRamp", title: "Vender" }
    ];

    const handleTabChange = (tabId: string) => {
        setActiveTab(tabId);
    };

    //input

    return {
        activeTab,
        tabs,
        handleTabChange
    };
};