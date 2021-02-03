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
    public class PostsController : Controller
    {
        private readonly Service _service;

        public PostsController ( Service service )
        {
            _service = service;
        }

        // GET: Posts
        public async Task<IActionResult> Index (int id)
        {
            var category = await _service.GetCategoryFromIdAsync(id);
            return View(category);
        }

        // GET: Posts/Details/5
        //public async Task<IActionResult> Details(int? id)
        //{
        //    if (id == null)
        //    {
        //        return NotFound();
        //    }

        //    var post = await _context.Post
        //        .Include(p => p.ID)
        //        .FirstOrDefaultAsync(m => m.PostID == id);
        //    if (post == null)
        //    {
        //        return NotFound();
        //    }

        //    return View(post);
        //}

        // GET: Posts/Create
        public IActionResult Create ( int id )
        {
            ViewBag.CategoryID = id;
            return View();
        }

        // POST: Posts/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create ( [Bind("Title,Content,CreatedWhen,Author,CategoryID")] Post post )
        {
            if (ModelState.IsValid)
            {
                var id = post.CategoryID;
                await _service.PostPostAsync(post);
                return RedirectToAction("Index", new { id });
            }
            return View(post);
        }

        // GET: Posts/Edit/5
        //public async Task<IActionResult> Edit(int? id)
        //{
        //    if (id == null)
        //    {
        //        return NotFound();
        //    }

        //    var post = await _context.Post.FindAsync(id);
        //    if (post == null)
        //    {
        //        return NotFound();
        //    }
        //    ViewData["CategoryID"] = new SelectList(_context.Category, "ID", "ID", post.CategoryID);
        //    return View(post);
        //}

        // POST: Posts/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        //[HttpPost]
        //[ValidateAntiForgeryToken]
        //public async Task<IActionResult> Edit(int id, [Bind("PostID,Title,Content,CreatedWhen,Author,CategoryID")] Post post)
        //{
        //    if (id != post.PostID)
        //    {
        //        return NotFound();
        //    }

        //    if (ModelState.IsValid)
        //    {
        //        try
        //        {
        //            _context.Update(post);
        //            await _context.SaveChangesAsync();
        //        }
        //        catch (DbUpdateConcurrencyException)
        //        {
        //            if (!PostExists(post.PostID))
        //            {
        //                return NotFound();
        //            }
        //            else
        //            {
        //                throw;
        //            }
        //        }
        //        return RedirectToAction(nameof(Index));
        //    }
        //    ViewData["CategoryID"] = new SelectList(_context.Category, "ID", "ID", post.CategoryID);
        //    return View(post);
        //}

        // GET: Posts/Delete/5
        //public async Task<IActionResult> Delete(int? id)
        //{
        //    if (id == null)
        //    {
        //        return NotFound();
        //    }

        //    var post = await _context.Post
        //        .Include(p => p.ID)
        //        .FirstOrDefaultAsync(m => m.PostID == id);
        //    if (post == null)
        //    {
        //        return NotFound();
        //    }

        //    return View(post);
        //}

        // POST: Posts/Delete/5
        //[HttpPost, ActionName("Delete")]
        //[ValidateAntiForgeryToken]
        //public async Task<IActionResult> DeleteConfirmed(int id)
        //{
        //    var post = await _context.Post.FindAsync(id);
        //    _context.Post.Remove(post);
        //    await _context.SaveChangesAsync();
        //    return RedirectToAction(nameof(Index));
        //}

        //private bool PostExists(int id)
        //{
        //    return _context.Post.Any(e => e.PostID == id);
        //}

        public async Task<ActionResult<Category>> Blog ( int id )
        {
            var post = await _service.GetPostFromIdAsync(id);
            return View(post);
        }

        public IActionResult RedirectToComment ( int id )
        {
            return RedirectToAction("Create", "Comments", new { id });
        }
    }
}
