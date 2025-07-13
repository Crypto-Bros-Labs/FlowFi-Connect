import React from "react";

interface HeaderModalProps {
    isModal: boolean,
    isFlow: boolean,
}

const HeaderModal: React.FC<HeaderModalProps> = ({ isModal, isFlow }) => {
    return (
        <>
            {isFlow && (
                <div className="flex justify-between items-center mb-3">
                    <button className="hover:bg-gray-100 rounded-full transition-colors">
                        <svg className="w-6 h-6 text-gray-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M15 19l-7-7 7-7" />
                        </svg>
                    </button>

                    {isModal && (
                        <button className="hover:bg-gray-100 rounded-full transition-colors">
                            <svg className="w-6 h-6 text-gray-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                            </svg>
                        </button>
                    )}

                    {!isModal && <div className="w-10 h-10"></div>}
                </div>
            )}

            {!isFlow && (
                <div className="w-10 h-15"></div>
            )}
        </>
    )

}

export default HeaderModal;