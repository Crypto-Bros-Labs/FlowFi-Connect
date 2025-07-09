import React from 'react';

interface InputAppProps {
    type?: string;
    placeholder?: string;
    label?: string;
    showLabel?: boolean;
    id?: string;
    name?: string;
    value?: string;
    onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
    className?: string;
}

const InputApp: React.FC<InputAppProps> = ({
    type = 'text',
    placeholder = '',
    label = '',
    showLabel = true,
    id,
    name,
    value,
    onChange,
    className = ''
}) => {
    return (
        <div className={`w-full ${className}`}>
            {showLabel && label && (
                <label
                    htmlFor={id}
                    className="block text-sm font-medium text-[#020F1E] mb-2"
                >
                    {label}
                </label>
            )}
            <input
                type={type}
                placeholder={placeholder}
                id={id}
                name={name}
                value={value}
                onChange={onChange}
                className="w-full px-3 md:px-4 py-2 md:py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#3E5EF5] focus:border-transparent text-sm md:text-base placeholder:text-xs md:placeholder:text-sm"
            />
        </div>
    );
};

export default InputApp;