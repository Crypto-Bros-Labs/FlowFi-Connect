import React from "react";
import HeaderModal from "../../../../shared/components/HeaderModal";
import DescriptionApp from "../../../../shared/components/DescriptionApp";
import SelectTile from "../../../../shared/components/SelectTile";
import ButtonApp from "../../../../shared/components/ButtonApp";
import { useSelectToken } from "../hooks/useSelectToken";

interface SelectTokenPanelProps {
    isModal?: boolean;
    isFlow?: boolean;
}

const SelectTokenPanel: React.FC<SelectTokenPanelProps> = ({ isModal = false, isFlow = false }) => {
    const {
        tokens,
        selectedToken,
        selectToken,
        handleBuy,
        handleSell
    } = useSelectToken();

    return (
        <div className="bg-white rounded-[1.25rem] w-full h-[80vh] max-w-md p-4 flex flex-col border-2 border-[#3E5EF5] shadow-lg">

            <HeaderModal isModal={isModal} isFlow={isFlow} />

            <DescriptionApp
                title='Selecciona tu token'
                description='Aqui puedes seleccionar el token que que quieras comprar o vender en la red que necesites'
            />

            {/* Contenedor con scroll para los tiles */}
            <div className="flex-1 overflow-y-auto mb-4 [&::-webkit-scrollbar]:hidden [-ms-overflow-style:none] [scrollbar-width:none]">
                <div className="space-y-3">
                    {tokens.map((token) => (
                        <SelectTile
                            key={token.id}
                            leading={
                                <div className={`w-10 h-10 ${token.color} rounded-full flex items-center justify-center`}>
                                    <span className={`font-bold text-lg ${selectedToken === token.id ? 'text-white' : 'text-white'}`}>
                                        {token.icon}
                                    </span>
                                </div>
                            }
                            title={
                                <div className="flex items-center gap-2">
                                    <span className={`font-semibold ${selectedToken === token.id ? 'text-white' : 'text-[#020F1E]'}`}>
                                        {token.symbol} •
                                    </span>
                                    <span className={`${selectedToken === token.id ? 'text-white' : 'text-[#495058]'}`}>
                                        {token.network}
                                    </span>
                                </div>
                            }
                            checked={selectedToken === token.id}
                            onClick={() => selectToken(token.id)}
                        />
                    ))}
                </div>
            </div>

            {/* Botones en el bottom */}
            <div className="flex gap-3 pt-4 pb-2">
                <div className="flex flex-col w-1/2">
                    <ButtonApp
                        text="Comprar"
                        textSize="text-sm"
                        paddingVertical="py-2"
                        isMobile={true}
                        onClick={handleBuy}
                    />
                </div>
                <div className="flex flex-col w-1/2">
                    <ButtonApp
                        text="Vender"
                        textSize="text-sm"
                        paddingVertical="py-2"
                        isMobile={true}
                        onClick={handleSell}
                    />
                </div>
            </div>
        </div>
    );
};

export default SelectTokenPanel;