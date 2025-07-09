import React from 'react';
import { IoCheckmarkCircle } from 'react-icons/io5';

interface SelectTileProps {
    leading?: React.ReactNode;
    title: React.ReactNode;
    checked?: boolean;
    onClick?: () => void;
    className?: string;
}

const SelectTile: React.FC<SelectTileProps> = ({
    leading,
    title,
    checked = false,
    onClick,
    className = ''
}) => {
    return (
        <div
            onClick={onClick}
            className={`
                flex items-center p-4 justify-between rounded-[15px] border transition-all duration-200 cursor-pointer
                ${checked
                    ? 'bg-[#3E5EF5] border-[#3E5EF5] text-white'
                    : 'bg-white border-gray-300 text-gray-800 hover:border-gray-400'
                }
                ${className}
            `}
        >
            {/* Left side - Leading icon y title */}
            <div className="flex items-center gap-3">
                {leading && (
                    <div className={`flex-shrink-0 ${checked ? 'text-white' : 'text-gray-600'}`}>
                        {leading}
                    </div>
                )}
                <div>
                    {title}
                </div>
            </div>

            {/* Right side - Check icon */}
            {checked && (
                <div className="flex-shrink-0 text-white">
                    <IoCheckmarkCircle size={30} />
                </div>
            )}
        </div>
    );
};

export default SelectTile;