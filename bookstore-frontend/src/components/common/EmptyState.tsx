import React from "react";
export default function EmptyState({title,desc}:{title:string,desc?:string}){
  return (
    <div className="text-center py-12">
      <div className="text-xl font-semibold">{title}</div>
      {desc && <p className="text-slate-600 mt-1">{desc}</p>}
    </div>
  )
}
