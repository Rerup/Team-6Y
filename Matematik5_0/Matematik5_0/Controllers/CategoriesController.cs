using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using Matematik5_0.Data;
using Matematik5_0.Models.Forum;
using Matematik5_0.Models;

namespace Matematik5_0.Controllers
{
    public class CategoriesController : Controller
    {
        private readonly Service _service;

        public CategoriesController ( Service service )
        {
            _service = service;
        }

        // GET: Categories
        public async Task<IActionResult> Index ( )
        {
            return View(await _service.GetCategoriesAsync());
        }

        // GET: Categories/Details/5
        public async Task<IActionResult> Details ( int? id )
        {
            if (id == null)
            {
                return NotFound();
            }

            var category = await _service.GetCategoryFromIdAsync(id);

            if (category == null)
            {
                return NotFound();
            }

            return View(category);
        }

        // GET: Categories/Create
        public IActionResult Create ( )
        {
            return View();
        }

        // POST: Categories/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create ( [Bind("Title,Description,CreatedWhen")] Category category )
        {
            //BIND SIKRE IMOD OVERPOSTING FX. I POSTMAND IKKE KAN SÆTTE IsAdmin = true;
            if (ModelState.IsValid)
            {
                await _service.PostCategoryAsync(category);
                return RedirectToAction(nameof(Index));
            }
            return View(category);
        }

        // GET: Categories/Edit/5
        public async Task<IActionResult> Edit ( int? id )
        {
            if (id == null)
            {
                return NotFound();
            }

            var category = await _service.GetCategoryFromIdAsync(id);

            if (category == null)
            {
                return NotFound();
            }
            return View(category);
        }

        // POST: Categories/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit ( int id, [Bind("ID,Title,Description,CreatedWhen")] Category category )
        {
            if (id != category.ID)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    await _service.PutCategoryAsync(id, category);
                }
                catch (DbUpdateConcurrencyException)
                {
                    throw;
                }
                return RedirectToAction(nameof(Index));
            }
            return View(category);
        }

        // GET: Categories/Delete/5
        public async Task<IActionResult> Delete ( int? id )
        {
            if (id == null)
            {
                return NotFound();
            }

            var category = await _service.GetCategoryFromIdAsync(id);

            if (category == null)
            {
                return NotFound();
            }

            return View(category);
        }

        // POST: Categories/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed ( int id )
        {
            await _service.DeleteCategoryFromIdAsync(id);
            return RedirectToAction(nameof(Index));
        }

        public IActionResult RedirectToPost ( int id )
        {
            return RedirectToAction("Index", "Posts", new { id });
        }
    }
}
