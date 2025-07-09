import React from 'react';
import { IoChevronDown } from 'react-icons/io5';

interface InputExchangeProps {
    symbol: string;
    icon?: React.ReactNode;
    value: string;
    onChange: (value: string) => void;
    onSelectToken: () => void;
    placeholder?: string;
    className?: string;
}

const InputExchange: React.FC<InputExchangeProps> = ({
    symbol,
    icon,
    value,
    onChange,
    onSelectToken,
    placeholder = "0.00",
    className = ''
}) => {
    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const inputValue = e.target.value;
        // Solo permitir números y un punto decimal
        if (/^\d*\.?\d*$/.test(inputValue)) {
            onChange(inputValue);
        }
    };

    return (
        <div className={`w-full bg-[#D6E6F8] border border-[#666666] rounded-[15px] p-3 flex items-center justify-between ${className}`}>
            {/* Token selector button */}
            <button
                onClick={onSelectToken}
                className="flex items-center gap-1 bg-white rounded-lg px-2 py-1 hover:bg-gray-50 transition-colors duration-200 flex-shrink-0"
            >
                {/* Icon/Image */}
                {icon && (
                    <div className="w-5 h-5 flex-shrink-0">
                        {icon}
                    </div>
                )}

                {/* Symbol */}
                <span className="font-medium text-sm text-[#020F1E] whitespace-nowrap">
                    {symbol}
                </span>

                {/* Chevron */}
                <IoChevronDown size={16} className="text-[#666666]" />
            </button>

            {/* Amount input */}
            <input
                type="text"
                inputMode="decimal"
                value={value}
                onChange={handleInputChange}
                placeholder={placeholder}
                className="bg-transparent text-right text-2xl font-semibold text-[#020F1E] placeholder:text-[#666666] focus:outline-none flex-1 min-w-0 ml-2"
            />
        </div>
    );
};

export default InputExchange;