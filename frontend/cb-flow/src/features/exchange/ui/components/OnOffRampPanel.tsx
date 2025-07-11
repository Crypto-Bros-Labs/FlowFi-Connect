import React from "react";
import HeaderModal from "../../../../shared/components/HeaderModal";
import SwitchTabs from "../../../../shared/components/SwitchTabs";
import { useOnOffRamp } from "../hooks/useOnOffRamp";
import InputExchange from "./InputExchange";

interface OnOffRampPanelProps {
    isModal?: boolean;
    isFlow?: boolean;
}

const OnOffRampPanel: React.FC<OnOffRampPanelProps> = ({ isModal = false, isFlow = false }) => {
    const {
        activeTab,
        tabs,
        handleTabChange,
        amount,
        setAmount,
        setShowCurrencySelector,
        amountFiat,
        setAmountFiat,
        setShowTokenSelector,
    } = useOnOffRamp();
    return (
        <div className="bg-white rounded-[1.25rem] w-full h-[80vh] max-w-md p-4 flex flex-col border-2 border-[#3E5EF5] shadow-lg">

            <HeaderModal isModal={isModal} isFlow={isFlow} />

            <div className="flex mb-6 justify-center">
                <div className="w-7/8 md:w-3/4">
                    <SwitchTabs
                        tabs={tabs}
                        activeTab={activeTab}
                        onTabChange={handleTabChange}
                    />
                </div>
            </div>

            <InputExchange
                symbol="BTC"
                icon={<img src="/bitcoin-icon.png" alt="BTC" className="w-full h-full" />}
                value={amount}
                onChange={setAmount}
                onSelectToken={() => setShowCurrencySelector(true)}
                placeholder="0.00"
                className="mb-4"
            />

            <InputExchange
                symbol="MXN"
                icon={<img src="/mexican-peso-icon.png" alt="MXN" className="w-full h-full" />}
                value={amountFiat}
                onChange={setAmountFiat}
                onSelectToken={() => setShowTokenSelector(true)}
                placeholder="0.00"
            />


        </div>
    );
};

export default OnOffRampPanel;