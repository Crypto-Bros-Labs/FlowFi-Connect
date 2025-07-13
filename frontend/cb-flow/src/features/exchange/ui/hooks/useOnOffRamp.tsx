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

    //inputs
    const [amount, setAmount] = useState("0.00");
    const [showCurrencySelector, setShowCurrencySelector] = useState(false);
    const [amountFiat, setAmountFiat] = useState("0.00");
    const [showTokenSelector, setShowTokenSelector] = useState(false);


    return {
        activeTab,
        tabs,
        handleTabChange,
        amount,
        setAmount,
        showCurrencySelector,
        setShowCurrencySelector,
        amountFiat,
        setAmountFiat,
        showTokenSelector,
        setShowTokenSelector,
    };
};