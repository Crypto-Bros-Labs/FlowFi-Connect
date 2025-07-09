import React from "react";
import HeaderModal from "../../../../shared/components/HeaderModal";
import SwitchTabs from "../../../../shared/components/SwitchTabs";
import { useOnOffRamp } from "../hooks/useOnOffRamp";

interface OnOffRampPanelProps {
    isModal?: boolean;
    isFlow?: boolean;
}

const OnOffRampPanel: React.FC<OnOffRampPanelProps> = ({ isModal = false, isFlow = false }) => {
    const { activeTab, tabs, handleTabChange } = useOnOffRamp();
    return (
        <div className="bg-white rounded-[1.25rem] w-full h-[80vh] max-w-md p-4 flex flex-col border-2 border-[#3E5EF5] shadow-lg">

            <HeaderModal isModal={isModal} isFlow={isFlow} />

            <div className="flex mb-4 justify-center">
                <div className="w-7/8 md:w-3/4">
                    <SwitchTabs
                        tabs={tabs}
                        activeTab={activeTab}
                        onTabChange={handleTabChange}
                    />
                </div>
            </div>




        </div>
    );
};

export default OnOffRampPanel;