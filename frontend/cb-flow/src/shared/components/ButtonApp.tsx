import React from 'react';

interface ButtonAppProps {
    text: string;
    paddingVertical?: string;
    paddingHorizontal?: string;
    backgroundColor?: string;
    stroke?: boolean;
    textColor?: string;
    onClick?: () => void;
    borderRadius?: string;
    isMobile?: boolean;
    textSize?: string;
}

const ButtonApp: React.FC<ButtonAppProps> = ({
    text,
    paddingVertical = 'py-3',
    paddingHorizontal = 'px-6',
    backgroundColor = 'bg-[#3E5EF5]',
    stroke = false,
    textColor = 'text-white',
    onClick,
    borderRadius = 'rounded-[3.125rem]',
    isMobile = false,
    textSize = 'text-base'
}) => {
    const strokeClass = stroke ? `border-1` : '';
    const cursorClass = onClick ? 'cursor-pointer' : '';
    const widthClass = isMobile ? 'w-full' : '';

    return (
        <button
            className={`
        ${paddingVertical} 
        ${paddingHorizontal} 
        ${textColor} 
        ${backgroundColor}
        ${borderRadius} 
        ${strokeClass} 
        ${cursorClass}
        ${widthClass}
        ${textSize}
        font-medium 
        transition-all 
        duration-200 
        hover:opacity-90 
        active:scale-95
      `}
            onClick={onClick}
        >
            {text}
        </button>
    );
};

export default ButtonApp;